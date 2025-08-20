import { Link } from "react-router-dom";
import { useGlobalContext } from "../context/GlobalContext";
import { useParams } from "react-router-dom";
export default function Category() {
  const { motos } = useGlobalContext();

  const { slug } = useParams();

  switch (motos.state) {
    case "loading":
      return (
        <>
          <h1>loading</h1>
        </>
      );

    case "success":
      const categories = motos.result;

      const category = categories.find((category) => {
        if (category.name.toLowerCase().replaceAll(" ", "-") === slug) {
          return true;
        }
      });

      const motorcycles = category.motos;

      console.log(motorcycles);

      motorcycles.map((category) => {
        const slug = category.name.toLowerCase().replaceAll(" ", "-");

        category.slug = slug;
      });

      console.log(motorcycles);

      return (
        <div className="container mt-5">
          <div className="row row-cols-1 row-cols-lg-3 g-2">
            {motorcycles.map((moto, index) => (
              <div className="col" key={index}>
                <div className="category-card">
                  <Link to={`/variants/${moto.slug}`}>
                    <p className="fw-bold  text-center">{moto.name} </p>
                    <img
                      src={`/motorcycles/${moto.imagePath}`}
                      alt={moto.name}
                    />
                  </Link>
                </div>
              </div>
            ))}
          </div>
        </div>
      );

    case "error":
      return (
        <>
          <h1>error</h1>
        </>
      );
    default:
      return null;
  }
}
