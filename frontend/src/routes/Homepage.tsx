import { Login } from "../components/Login";
import { Store } from "../components/Store";

export function Homepage() {
	return (
		<>
			<h1>Welcome to the fruits store!</h1>
			<Login />
			<Store />
		</>
	);
}
