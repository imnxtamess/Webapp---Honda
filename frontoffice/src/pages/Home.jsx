export default function Home() {
  return (
    <>
      <main className="homeBg">
        <div className="video-wrapper">
          <video autoPlay muted loop playsInline>
            <source src="/hero-video.mp4" type="video/mp4" />
          </video>
        </div>

        <div className="img-wrapper mt-4 mt-lg-0">
          <img
            src="/hero-image.webp"
            alt="honda innovation banner image"
            className="w-100"
          />
        </div>
        <div className="container-fluid">
          <div className="row text-center mt-3 mt-lg-5">
            <div className="col-12"></div>
            <div className="col-12">
              <h1>Honda DreamTech.</h1>
              <h1>Beyond engineering.</h1>
              <p className="mx-3 mx-lg-4 mt-4">
                All Honda innovations start with you, the rider. Our engineers
                constantly ask, “How can riding be even greater?” From there,
                the journey evolves. Years of research and development, racing
                expertise and rider feedback, fuels every technology created by
                the development team. It never stops — and neither do we. This
                led to inventions like Honda E-Clutch, DCT, and the new V3
                Engine with electrical compressor concept. Our challenging
                spirit to enhance every moment, will never stop. This is our
                vision for progress and we call it Honda DreamTech.
              </p>
            </div>
          </div>
        </div>
        <div className="container-fluid">
          <div className="row mt-3 mt-lg-5 text-center">
            <div className="col-lg-6">
              <img src="/yume.jpg" alt="" className="w-100" />
            </div>
            <div className="col-lg-6 mt-3 d-flex flex-column justify-content-center">
              <h2>Yume</h2>
              <p className="mt-3 mx-3 mx-lg-5">
                ‘Yume’ is the Japanese word for ‘dream’. It is deeply linked to
                Honda’s origins, and a handwritten version of it by the founder
                Soichiro Honda can be seen at the entrance of the Honda
                Collection Hall located at the Mobility Resort Motegi in
                Tochigi, Japan. It later inspired our corporate slogan ‘The
                Power of Dreams’, which is the driving force for the development
                of Honda’s motorcycle technology, whose purpose is to enable our
                customers to enjoy life with more freedom, more convenience and
                more fun. <br />
                It is also present on Honda’s first fully-fledged product, the
                Dream D-type motorcycle. When the Dream D was launched in 1949,
                Soichiro Honda began talking about being ‘number one in the
                world’. His dream became reality through innovation, technology
                and engineering, from people to people. Having produced more
                than 500 million units worldwide, Honda will continue to lead
                the world with high quality products and services, exploring new
                possibilities for motorcycles.
              </p>
            </div>
          </div>
        </div>
      </main>
    </>
  );
}
