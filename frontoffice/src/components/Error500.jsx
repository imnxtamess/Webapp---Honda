import { Link } from "react-router-dom";

export default function Error500() {
  return (
    <>
      <div className="container py-5 honda-404">
        <div className="row justify-content-center">
          <div className="col-12 col-md-10 col-lg-8">
            <div className="spec-card p-4 p-md-5 text-center position-relative overflow-hidden">
              <div className="brand-stripe mb-3" aria-hidden="true"></div>

              <div
                className="display-1 fw-bolder lh-1 brand-404"
                aria-label="404"
              >
                <span className="digit">5</span>
                <span className="digit">0</span>
                <span className="digit">0</span>
              </div>

              <h1 className="fw-bolder mb-2 text-light">Server Error</h1>
              <p className="mb-4 text-light">
                Our server must have crashed into a wall, our mechanics are
                working on fixing it, hang in there!
              </p>

              <div className="d-flex gap-3 justify-content-center">
                <Link to={"/"} className="btn btn-honda-white">
                  Back to Home
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
