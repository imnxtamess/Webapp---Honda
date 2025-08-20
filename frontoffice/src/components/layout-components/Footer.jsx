export default function Footer() {
  return (
    <>
      <footer>
        <div className="flying-bg-wrapper">
          <img src="/flying-bg.avif" alt="" className="flying-bg" />
          <div className="flying-text">
            <h2 className="fw-bold">
              Join us on the track, trail, or scenic route and ignite your
              journey with a Honda
            </h2>
          </div>
          <div className="flying-img">
            <img src="/flying.avif" alt="" />
          </div>
        </div>
        <div id="rideRed">
          <div className="container d-flex justify-content-between align-items-center">
            <h3>#RideRed</h3>
            <a href="">
              <i className="bi bi-instagram"></i>{" "}
              <span className="ms-3">@honda_motorcycles</span>
            </a>
          </div>
        </div>
      </footer>
    </>
  );
}
