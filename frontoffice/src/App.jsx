import { BrowserRouter, Routes, Route } from "react-router-dom";

import DefaultLayout from "./layouts/DefaultLayout";
import Home from "./pages/Home";
import { GlobalProvider } from "./context/GlobalContext";
import Categories from "./pages/Categories";
import Category from "./pages/Category";
import Moto from "./pages/Moto";
import Error404 from "./components/Error404";

export default function App() {
  return (
    <GlobalProvider>
      <BrowserRouter>
        <Routes>
          <Route element={<DefaultLayout />}>
            <Route path="/" element={<Home />} />
            <Route path="/categories" element={<Categories />} />
            <Route path="/categories/:slug" element={<Category />} />
            <Route
              path="/categories/:categorySlug/:motoSlug"
              element={<Moto />}
            />
            <Route path="*" element={<Error404 />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GlobalProvider>
  );
}
