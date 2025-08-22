import { Link } from "react-router-dom";
import { useGlobalContext } from "../context/GlobalContext";
import Error404 from "../components/Error404";
export default function Categories() {
  const { motos } = useGlobalContext();

  switch (motos.state) {
    case "loading":
      return (
        <>
          <h1>loading</h1>
        </>
      );

    case "success":
      const categories = motos.result;
      console.log(categories);

      categories.map((category) => {
        const slug = category.name.toLowerCase().replaceAll(" ", "-");

        category.slug = slug;
      });

      console.log(categories);

      return (
        <div className="container my-5">
          <div className="row row-cols-2 row-cols-lg-3 g-2">
            {categories.map((category, index) => (
              <div className="col" key={index}>
                <div className="category-card">
                  <Link to={`/categories/${category.slug}`}>
                    <p className="fw-bold fs-4 text-center">
                      {category.name}{" "}
                      <span className="text-muted">
                        ({category.motos.length})
                      </span>
                    </p>
                    <img
                      src={`categories/${category.imagePath}`}
                      alt={category.name}
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
          <Error404 />
        </>
      );
    default:
      return null;
  }
}
