import { Link } from "react-router-dom";
import { useGlobalContext } from "../context/GlobalContext";
import { useParams } from "react-router-dom";
import { useState } from "react";

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
                        <div>
                          <strong>Cruise Control: </strong>
                          {variant.cruiseControl ? "Yes" : "No"}
                        </div>
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
                        <div>
                          <strong>Headlights: </strong>
                          {variant.headLights}
                        </div>
                        <div>
                          <strong>Manual Transmission: </strong>
                          {variant.manualTransmission ? "Yes" : "No"}
                        </div>
                        <div>
                          <strong>Quick Shifter: </strong>
                          {variant.quickShifter ? "Yes" : "No"}
                        </div>
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

                        {/* cruiseControl : true frameType : "Aluminum"
                        frontSuspension : "Inverted Telescopic" headLights
                        :"LED" id : 1 manualTransmission : true name :
                        "CBR1000RR Standard" price : 16000 quickShifter : true
                        rearSuspension : "Mono-shock" seatHeight : 820
                        tankCapacity : 17 weight : 197 */}
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
          <h1>error</h1>
        </>
      );
    default:
      return null;
  }
}
