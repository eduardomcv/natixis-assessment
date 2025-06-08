import { useStore } from "../../hooks/useStore";
import classes from "./Store.module.css";

export function Store() {
	const store = useStore();

	return (
		<ul className={classes.itemList}>
			{Object.entries(store).map(([id, item]) => (
				<li key={id} className={classes.item}>
					{item.name} - {item.price}
				</li>
			))}
		</ul>
	);
}
