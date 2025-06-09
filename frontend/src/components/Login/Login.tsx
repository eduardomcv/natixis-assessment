import { urls } from "../../config";
import { useFetch } from "../../hooks/useFetch";
import classes from "./Login.module.css";

export function Login() {
	const [fetch] = useFetch({
		url: urls.api.login(),
		method: "POST",
	});

	async function login(formData: FormData) {
		const username = formData.get("username");
		const password = formData.get("password");

		if (typeof username !== "string" || typeof password !== "string") {
			throw new Error("Invalid form data");
		}

		return fetch({ username, password });
	}

	return (
		<form className={classes.form} action={login}>
			<h2>Login</h2>
			<input type="text" required name="username" placeholder="Username" />
			<input type="password" required name="password" placeholder="Password" />
			<button type="submit">Login</button>
		</form>
	);
}
