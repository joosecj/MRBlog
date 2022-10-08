import'./styles.css';

function Listing() {

    return (
      <><h1 className="news-title">Últimas notícias</h1>
      <section id="news">
        <div className="container">
          <div className="dsplaygames-card-container">
            <div className="dsplaygames-card">
              <h2>Título principal da notícia</h2>
              <p className="dsplaygames-line">Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto vitae ducimus aperiam.</p>
              <p className="dsplaygames-date">23/02/2022</p>
              <div className="dsplaygames-card-description">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto vitae ducimus aperiam. Harum, in dicta? Perspiciatis nam, facilis nesciunt, expedita esse labore officiis ut ipsam voluptates aperiam laboriosam sequi! Expedita vitae iste quae veniam molestiae ea ad alias maiores. Numquam dolor eaque recusandae eius nihil quibusdam esse nisi asperiores in nulla dicta distinctio aliquid ullam aspernatur ad amet fugit dolorem sapiente veritatis quo, rem a. Molestiae qui sed enim! Laborum doloribus nisi quia dolore obcaecati reiciendis quae, numquam consectetur sed animi temporibus inventore nemo. Asperiores fugit rerum, placeat quas in nobis obcaecati similique quae assumenda culpa ab sit voluptatum molestiae voluptates facilis optio recusandae tempore nihil atque et nesciunt nemo perferendis adipisci harum. Corrupti eum autem veritatis et beatae reiciendis, deleniti corporis, voluptas, eaque ipsa nemo odio? Eligendi sapiente id enim quibusdam, repudiandae optio error! Vitae perspiciatis voluptatem sequi nostrum necessitatibus culpa fuga ...</p>
                <a href="">Ler mais...</a>
              </div>
            </div>
          </div>
        </div>
      </section></>
    );
}

export default Listing;