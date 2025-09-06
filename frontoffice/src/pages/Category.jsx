import { Link } from "react-router-dom";
import { useGlobalContext } from "../context/GlobalContext";
import { useParams } from "react-router-dom";
import Error500 from "../components/Error500";
import Error404 from "../components/Error404";
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

      if (!category) {
        return <Error404 />;
      }

      const motorcycles = category.motos;

      motorcycles.map((category) => {
        const slug = category.name.toLowerCase().replaceAll(" ", "-");

        category.slug = slug;
      });

      console.log(motorcycles);

      return (
        <div className="container my-5">
          <div className="row row-cols-1 row-cols-lg-3 g-2">
            {motorcycles.map((moto, index) => (
              <div className="col" key={index}>
                <div className="category-card">
                  <Link to={`/variants/${moto.slug}`}>
                    <p className="fw-bold  text-center">{moto.name} </p>
                    <img
                      src={`/motorcycles/${moto.variants[0].colorVariants[0].imagePath}`}
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
          <Error500 />
        </>
      );
    default:
      return null;
  }
}
