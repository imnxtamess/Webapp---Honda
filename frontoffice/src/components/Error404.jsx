import { Link } from "react-router-dom";

export default function Error404() {
  return (
    <>
      <div className="container py-5 honda-404 text-center">
        <div className="display-1 fw-bolder lh-1 brand-404" aria-label="404">
          <span className="digit">4</span>
          <span className="digit">0</span>
          <span className="digit">4</span>
        </div>

        <h1 className="fw-bolder mb-2 text-light">Page not found</h1>
        <p className="mb-4 text-light">
          The page you’re looking for rode off into the sunset. Check the URL,
          or head back to the garage.
        </p>

        <div className="d-flex gap-3 justify-content-center">
          <Link to={"/"} className="btn btn-honda-white">
            Back to Home
          </Link>
          <Link to="/categories" className="btn btn-outline-dark text-light">
            Browse Motorcycles
          </Link>
        </div>
      </div>
      );
    </>
  );
}
