import { useEffect } from "react";
import { useFetch } from "../../hooks/useFetch";
import classes from "./Store.module.css";
import { Link } from "react-router";
import { urls } from "../../config";

export type ID = string;

export interface Item {
	name: string;
	price: string;
	currency: string;
	imageURL: string | null;
}

export type Store = Record<ID, Item>;

export function Store() {
	const [runFetch, { data, loading, error, idle }] = useFetch<Store>({
		url: urls.api.store(),
	});

	useEffect(() => {
		runFetch();
	}, [runFetch]);

	if (idle || loading) {
		// in this component, we are running the fetch when the component mounts
		// so it will only be idle on the initial render
		return <p>Loading...</p>;
	}

	if (error) {
		return <p>Error: {error.message}</p>;
	}

	return (
		<ul className={classes.grid}>
			{Object.entries(data).map(([id, item]) => {
				const currencyFormatter = new Intl.NumberFormat("pt-PT", {
					style: "currency",
					currency: item.currency,
				});

				return (
					<li key={id} className={classes.item}>
						<Link to={`/item/${id}`} className={classes.itemLink}>
							<img
								src={item.imageURL ?? ""}
								alt={item.name}
								className={classes.itemImage}
							/>
						</Link>
						<Link to={`/item/${id}`} className={classes.itemName}>
							{item.name}
						</Link>
						<Link to={`/item/${id}`} className={classes.itemPrice}>
							{currencyFormatter
								.formatToParts(Number(item.price))
								.map((part) => {
									let className = "";

									switch (part.type) {
										case "currency":
											className = classes.priceCurrency;
											break;
										case "integer":
											className = classes.priceInteger;
											break;
										case "fraction":
											className = classes.priceFraction;
											break;
									}

									return (
										<span key={part.value} className={className}>
											{part.value}
										</span>
									);
								})}
						</Link>
					</li>
				);
			})}
		</ul>
	);
}
