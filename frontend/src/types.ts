export type ID = string;

export interface Item {
	name: string;
	price: string;
	currency: string;
	imageURL: string | null;
}

export type Store = Record<ID, Item>;
