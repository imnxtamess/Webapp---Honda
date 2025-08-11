import { useGlobalContext } from "../context/GlobalContext";

export default function Motorcycles() {
  const { motos } = useGlobalContext();

  switch (motos.state) {
    case "loading":
      console.log(motos.state);

      return (
        <>
          <h1>loading</h1>
        </>
      );

    case "success":
      console.log(motos);
      console.log(motos.state);
      console.log(motos.result[0]);

      return (
        <>
          <h1>success</h1>
        </>
      );
    case "error":
      console.log(motos.state);
      return (
        <>
          <h1>error</h1>
        </>
      );
    default:
      return null;
  }
}
