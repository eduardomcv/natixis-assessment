import { useEffect } from "react";
import { useParams } from "react-router";
import { useFetch } from "../../hooks/useFetch";
import { urls } from "../../config";
import type { Item } from "../../types";

import classes from "./ItemDetails.module.css";

export function ItemDetails() {
	const { id } = useParams<{ id: string }>();

	const [runFetch, { data, error }] = useFetch<Item>({
		url: urls.api.item(id!),
	});

	useEffect(() => {
		runFetch();
	}, [runFetch]);

	if (error) {
		return <p>Error loading item details: {error.message}</p>;
	}

	if (!data) {
		return null;
	}

	const currencyFormatter = new Intl.NumberFormat("pt-PT", {
		style: "currency",
		currency: data.currency,
	});

	return (
		<div className={classes.container}>
			<div className={classes.header}>
				<div>
					<h2>{data.name}</h2>
					<p>{currencyFormatter.format(Number(data.price))}</p>
				</div>
				<img
					className={classes.itemImage}
					src={data.imageURL ?? ""}
					alt={data.name}
				/>
			</div>
			<h2>Description</h2>
			<p>
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean
				ultricies nulla nec mollis posuere. Pellentesque vehicula fringilla
				massa, ut feugiat dui ornare a. Nullam feugiat diam eu risus varius
				suscipit. Nulla eleifend gravida dui, ac scelerisque leo commodo
				pretium. Donec tempus fermentum eros, nec consequat massa. Quisque
				vehicula turpis sit amet quam egestas, bibendum sodales lorem commodo.
				Curabitur in est lobortis, fermentum lectus at, elementum eros. Praesent
				posuere fringilla ante. Nunc sit amet ultrices massa.{" "}
			</p>
		</div>
	);
}
