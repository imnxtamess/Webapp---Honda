import { createContext, useContext, useState, useEffect } from "react";
const GlobalContext = createContext();

const API_URL = import.meta.env.VITE_API_URL;

const GlobalProvider = ({ children }) => {
  const [motos, setMotos] = useState({
    state: "loading",
  });

  useEffect(() => {
    fetch(`${API_URL}categories`)
      .then((res) => res.json())
      .then((data) => {
        setMotos({
          state: "success",
          result: data,
        });
      })
      .catch((err) => {
        setMotos({
          state: "error",
          message: err.message,
        });
        console.error(err);
      });
  }, []);

  return (
    <GlobalContext.Provider value={{ motos }}>
      {children}
    </GlobalContext.Provider>
  );
};

function useGlobalContext() {
  return useContext(GlobalContext);
}

export { useGlobalContext, GlobalProvider };
