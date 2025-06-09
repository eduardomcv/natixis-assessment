import { useParams } from "react-router";
import { useFetch } from "../hooks/useFetch";

export function ItemDetails() {
	const { id } = useParams();

	const [runFetch, { data, loading, error, idle }] = useFetch<Item>({
		url: `http://localhost:8080/store/${id}`,
	});

	return (
		<div>
			<h1>Item Details</h1>
			<p>This is where the item details will be displayed.</p>
		</div>
	);
}
