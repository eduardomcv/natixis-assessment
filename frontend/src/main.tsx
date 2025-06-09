import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router";

import { Homepage } from "./routes/Homepage.tsx";
import { ItemDetails } from "./routes/ItemDetails.tsx";
import "./index.css";

createRoot(document.getElementById("root")!).render(
	<StrictMode>
		<BrowserRouter>
			<Routes>
				<Route index element={<Homepage />} />
				<Route path="/item/:id" element={<ItemDetails />} />
			</Routes>
		</BrowserRouter>
	</StrictMode>,
);
