import { useState, useCallback } from "react";

interface IdleState {
	data: undefined;
	error: undefined;
	loading: false;
	idle: true;
}

interface LoadingState {
	data: undefined;
	error: undefined;
	loading: true;
	idle: false;
}

interface ErrorState {
	data: undefined;
	error: Error;
	loading: false;
	idle: false;
}

interface SuccessState<T> {
	data: T;
	error: undefined;
	loading: false;
	idle: false;
}

export type FetchState<T> =
	| IdleState
	| LoadingState
	| ErrorState
	| SuccessState<T>;

export interface UseFetchOptions {
	url: URL | string;
	method?: string;
}

/**
 * A simple wrapper for the fetch API that handles fetching states.
 *
 * @param options - Options for the fetch request.
 * @returns A tuple containing the current fetch state and a function to trigger the fetch.
 */
export function useFetch<T>(options: UseFetchOptions) {
	const { url, method = "GET" } = options;

	const [state, setState] = useState<FetchState<T>>({
		data: undefined,
		error: undefined,
		loading: false,
		idle: true,
	});

	const run = useCallback(
		async (bodyObject?: Record<string, unknown>) => {
			setState({
				data: undefined,
				loading: true,
				error: undefined,
				idle: false,
			});

			try {
				const body = bodyObject ? JSON.stringify(bodyObject) : undefined;

				const response = await fetch(url, {
					method,
					body,
					headers: {
						"Content-Type": "application/json",
					},
				});

				const data = (await response.json()) as T;

				setState({
					data,
					error: undefined,
					loading: false,
					idle: false,
				});
			} catch (error) {
				setState({
					data: undefined,
					loading: false,
					error: error instanceof Error ? error : new Error("Unknown error"),
					idle: false,
				});
			}
		},
		[url, method],
	);

	return [run, state] as const;
}
