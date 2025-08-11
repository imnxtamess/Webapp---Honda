import { BrowserRouter, Routes, Route } from "react-router-dom";

import DefaultLayout from "./layouts/DefaultLayout";
import Home from "./pages/Home";
import Motorcycles from "./pages/Motorcycles";
import OurStory from "./pages/OurStory";
import { GlobalProvider } from "./context/GlobalContext";

export default function App() {
  return (
    <GlobalProvider>
      <BrowserRouter>
        <Routes>
          <Route element={<DefaultLayout />}>
            <Route path="/" element={<Home />} />
            <Route path="/motorcycles" element={<Motorcycles />} />
            <Route path="/our-story" element={<OurStory />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GlobalProvider>
  );
}
