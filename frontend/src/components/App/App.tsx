import { Routes, Route, Link } from "react-router";
import { Homepage } from "../../routes/Homepage";
import { ItemDetails } from "../../routes/ItemDetails";

import classes from "./App.module.css";

export function App() {
	return (
		<>
			<header className={classes.header}>
				<Link to="/">
					<h1>Fruits store</h1>
				</Link>
			</header>
			<Routes>
				<Route index element={<Homepage />} />
				<Route path="/item/:id" element={<ItemDetails />} />
			</Routes>
		</>
	);
}
