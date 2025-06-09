import { useEffect } from "react";
import { useFetch } from "../../hooks/useFetch";
import { Link } from "react-router";
import { urls } from "../../config";
import type { Store } from "../../types";

import classes from "./Store.module.css";

export function Store() {
	const [runFetch, { data, error }] = useFetch<Store>({
		url: urls.api.store(),
	});

	useEffect(() => {
		runFetch();
	}, [runFetch]);

	if (error) {
		return <p>Error: {error.message}</p>;
	}

	if (!data) {
		return (
			<ul className={classes.grid}>
				{Array.from({ length: 6 })
					.fill(0)
					.map((_, index) => (
						<li key={index} className={classes.item}>
							<div className={classes.itemImage} />
							<div className={classes.itemName} />
							<div className={classes.itemPrice} />
						</li>
					))}
			</ul>
		);
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
