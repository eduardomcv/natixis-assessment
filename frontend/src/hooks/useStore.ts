import { useState, useEffect } from "react";

export type ID = string;

export interface Item {
	name: string;
	price: string;
}

export type Store = Record<ID, Item>;

export function useStore(): Store {
	const [store, setStore] = useState<Store>({});

	useEffect(() => {
		fetch("http://localhost:8080/store")
			.then((res) => res.json())
			.then((data) => setStore(data));
	}, []);

	return store;
}
