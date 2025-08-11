import { NavLink } from "react-router-dom";

export default function Header() {
  return (
    <>
      <header>
        <nav className="navbar navbar-expand-sm navbar-dark  p-4">
          <div className="container-fluid">
            <a className="navbar-brand" href="/">
              <img src="/honda-nav-logo.png" alt="honda logo" />
            </a>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarID"
              aria-controls="navbarID"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarID">
              <div className="navbar-nav align-items-lg-end h-100 gap-lg-3 ms-5">
                <NavLink
                  className="nav-link active bebas-neue-text p-0 pt-3"
                  aria-current="page"
                  to={"/"}
                >
                  Home
                </NavLink>
                <NavLink
                  className="nav-link active bebas-neue-text p-0 pt-3"
                  aria-current="page"
                  to={"/motorcycles"}
                >
                  Motorcycles
                </NavLink>
                <NavLink
                  className="nav-link active bebas-neue-text p-0 pt-3"
                  aria-current="page"
                  to={"/our-story"}
                >
                  Our story
                </NavLink>
              </div>
            </div>
          </div>
        </nav>
      </header>
    </>
  );
}
