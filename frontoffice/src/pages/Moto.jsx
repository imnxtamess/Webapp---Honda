import { Link } from "react-router-dom";
import { useGlobalContext } from "../context/GlobalContext";
import { useParams } from "react-router-dom";
import { useState } from "react";
import Error404 from "../components/Error404";
export default function Moto() {
  const { motos } = useGlobalContext();
  const { slug } = useParams();
  const [activeImg, setActiveImg] = useState("");
  switch (motos.state) {
    case "loading":
      return (
        <>
          <h1>loading</h1>
        </>
      );

    case "success":
      const categories = motos.result;

      const allMotos = categories.flatMap((categories) => categories.motos);

      const moto = allMotos.find((moto) => {
        return moto.name.toLowerCase().replaceAll(" ", "-") === slug;
      });

      console.log(moto);

      return (
        <>
          <div className="moto-hero-container">
            <img
              className="moto-hero"
              src={`/motorcycles/hero-${moto.imagePath}`}
              alt=""
            />
            <h2 className="moto-hero-text">{moto.name}</h2>
          </div>

          <div className="container mt-5">
            <h3 className="fw-bold fs-2 text-center">VARIANTS</h3>
            <div className="mx-3 variant-selector">
              <img
                src={`/motorcycles/${moto.imagePath}`}
                alt={moto.name}
                className={activeImg.includes("variant") ? "d-none" : "d-block"}
              />
              <img
                className={activeImg.includes("variant") ? "d-block" : "d-none"}
                src={`/motorcycles/variant-${moto.imagePath}`}
                alt={moto.name}
              />
              <span className="text-center fw-bold fs-4">
                {activeImg.includes("variant")
                  ? moto.variants[0].colorVariants[1].name
                  : moto.variants[0].colorVariants[0].name}
                {}
              </span>
              <div className="thumb-container gap-3">
                <img
                  onClick={() => {
                    setActiveImg(`/motorcycles/${moto.imagePath}`);
                  }}
                  className={
                    activeImg.includes("variant")
                      ? "border-light"
                      : "border-dark"
                  }
                  src={`/motorcycles/${moto.imagePath}`}
                  alt=""
                />
                <img
                  onClick={() => {
                    setActiveImg(`/motorcycles/variant-${moto.imagePath}`);
                  }}
                  className={
                    activeImg.includes("variant")
                      ? "border-dark"
                      : "border-light"
                  }
                  src={`/motorcycles/variant-${moto.imagePath}`}
                  alt=""
                />
              </div>
            </div>
          </div>
          <section className="specs-section pb-5">
            <h3 className="mt-5 mb-0 fw-bold fs-2 text-center py-5">
              SPECIFICATIONS
            </h3>
            <div className="container">
              <div className="row row-cols-1 row-cols-lg-2">
                {moto.variants.map((variant, index) => (
                  <div className="col" key={index}>
                    <div className="spec-card">
                      <div className="px-5">
                        <h4 className="fw-bolder">{variant.name}</h4>
                        <span className="fw-bold">{variant.price} &euro;</span>
                      </div>
                      <div className="spec-card-head">
                        <img
                          src={`/motorcycles/${moto.imagePath}`}
                          alt={moto.name}
                        />
                      </div>
                      <div className="specs d-flex flex-column align-items-start mt-3 px-5">
                        <div
                          className="accordion"
                          id={`specsAccordion-${index}`}
                        >
                          <div className="accordion-item engine">
                            <h2
                              className="accordion-header"
                              id={`headingEngine-${index}`}
                            >
                              <button
                                className="accordion-button"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target={`#collapseEngine-${index}`}
                                aria-expanded="true"
                                aria-controls={`collapseEngine-${index}`}
                              >
                                Engine
                              </button>
                            </h2>
                            <div
                              id={`collapseEngine-${index}`}
                              className="accordion-collapse collapse show"
                              aria-labelledby={`headingEngine-${index}`}
                              data-bs-parent={`#specsAccordion-${index}`}
                            >
                              <div className="accordion-body">
                                <div>
                                  <strong>Engine: </strong>
                                  {moto.engine.engineDisplacement}cc{" "}
                                  {moto.engine.engineType}
                                </div>
                                <div>
                                  <strong>Max Power: </strong>
                                  {moto.engine.maxPower}
                                </div>
                                <div>
                                  <strong>Max Torque: </strong>
                                  {moto.engine.maxTorque}
                                </div>
                                <div>
                                  <strong>Fuel System: </strong>
                                  {moto.engine.fuelSystem}
                                </div>
                                <div>
                                  <strong>Compression Ratio: </strong>
                                  {moto.engine.compressionRatio}
                                </div>
                              </div>
                            </div>
                          </div>

                          <div className="accordion-item dimensions">
                            <h2
                              className="accordion-header"
                              id={`headingDimensions-${index}`}
                            >
                              <button
                                className="accordion-button collapsed"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target={`#collapseDimensions-${index}`}
                                aria-expanded="false"
                                aria-controls={`collapseDimensions-${index}`}
                              >
                                Dimensions
                              </button>
                            </h2>
                            <div
                              id={`collapseDimensions-${index}`}
                              className="accordion-collapse collapse"
                              aria-labelledby={`headingDimensions-${index}`}
                              data-bs-parent={`#specsAccordion-${index}`}
                            >
                              <div className="accordion-body">
                                <div>
                                  <strong>Seat Height: </strong>
                                  {variant.seatHeight} mm
                                </div>
                                <div>
                                  <strong>Tank Capacity: </strong>
                                  {variant.tankCapacity} L
                                </div>
                                <div>
                                  <strong>Weight: </strong>
                                  {variant.weight} kg
                                </div>
                              </div>
                            </div>
                          </div>

                          <div className="accordion-item chassis">
                            <h2
                              className="accordion-header"
                              id={`headingChassis-${index}`}
                            >
                              <button
                                className="accordion-button collapsed"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target={`#collapseChassis-${index}`}
                                aria-expanded="false"
                                aria-controls={`collapseChassis-${index}`}
                              >
                                Chassis
                              </button>
                            </h2>
                            <div
                              id={`collapseChassis-${index}`}
                              className="accordion-collapse collapse"
                              aria-labelledby={`headingChassis-${index}`}
                              data-bs-parent={`#specsAccordion-${index}`}
                            >
                              <div className="accordion-body">
                                <div>
                                  <strong>Frame Type: </strong>
                                  {variant.frameType}
                                </div>
                                <div>
                                  <strong>Front Suspension: </strong>
                                  {variant.frontSuspension}
                                </div>
                                <div>
                                  <strong>Rear Suspension: </strong>
                                  {variant.rearSuspension}
                                </div>
                              </div>
                            </div>
                          </div>

                          <div className="accordion-item features">
                            <h2
                              className="accordion-header"
                              id={`headingFeatures-${index}`}
                            >
                              <button
                                className="accordion-button collapsed"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target={`#collapseFeatures-${index}`}
                                aria-expanded="false"
                                aria-controls={`collapseFeatures-${index}`}
                              >
                                Features
                              </button>
                            </h2>
                            <div
                              id={`collapseFeatures-${index}`}
                              className="accordion-collapse collapse"
                              aria-labelledby={`headingFeatures-${index}`}
                              data-bs-parent={`#specsAccordion-${index}`}
                            >
                              <div className="accordion-body">
                                <div>
                                  <strong>Headlights: </strong>
                                  {variant.headLights}
                                </div>
                                <div>
                                  <strong>Cruise Control: </strong>
                                  {variant.cruiseControl ? "Yes" : "No"}
                                </div>
                                <div>
                                  <strong>Quick Shifter: </strong>
                                  {variant.quickShifter ? "Yes" : "No"}
                                </div>
                                <div>
                                  <strong>Manual Transmission: </strong>
                                  {variant.manualTransmission ? "Yes" : "No"}
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </section>
        </>
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
