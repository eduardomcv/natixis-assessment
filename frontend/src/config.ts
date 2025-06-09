// should be replaced with an actual URL in production
const API_BASE_URL = "http://localhost:8080";

export const urls = {
	api: {
		baseurl() {
			return API_BASE_URL;
		},
		store() {
			return `${API_BASE_URL}/store`;
		},
		createItem() {
			return `${API_BASE_URL}/create-item`;
		},
		item(id: string) {
			return `${API_BASE_URL}/item/${id}`;
		},
	},
};
