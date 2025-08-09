export default function Home() {
  return (
    <>
      <main className="homeBg">
        <div className="video-wrapper">
          <video autoPlay muted loop playsInline>
            <source src="/hero-video.mp4" type="video/mp4" />
          </video>
        </div>
        <div className="container pt-5">
          <div className="row">
            <div className="col-lg-8">
              <div className="img-wrapper">
                <img
                  src="/hero-image.webp"
                  alt="honda innovation banner image"
                  className="w-100 rounded"
                />
              </div>
            </div>
            <div className="col-lg-4">
              <p>
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Sit
                saepe eius illo sed esse quos iure culpa sint modi nemo,
                blanditiis, mollitia ducimus exercitationem similique corrupti
                praesentium eos adipisci numquam? Neque rem pariatur, voluptate
                iste porro consequuntur obcaecati enim quam, repellat quae
                debitis fugiat labore, tenetur expedita impedit quibusdam quo
                quod minus laudantium quos omnis eum reprehenderit aperiam?
                Soluta earum autem, voluptatum obcaecati beatae cum quam
                praesentium neque commodi nam, nesciunt placeat quia minima
                dolorum debitis explicabo mollitia ipsam odit nihil ipsum?
                Cupiditate alias aliquam labore vel error quas veritatis autem.
                Repellat suscipit accusantium odio aspernatur doloribus dicta ex
                dignissimos.
              </p>
            </div>
          </div>
        </div>
      </main>
    </>
  );
}
