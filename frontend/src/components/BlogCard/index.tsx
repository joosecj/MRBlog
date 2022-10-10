import'./styles.css';
import { Link } from "react-router-dom";

const blog = {
    id: 1,
    title: "The Witcher",
    titleDescription: "Ldsrer dsarerw fdsgadsasd",
    description: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto vitae ducimus aperiam. Harum, in dicta? Perspiciatis nam, facilis nesciunt, expedita esse labore officiis ut ipsam voluptates aperiam laboriosam sequi! Expedita vitae iste quae veniam molestiae ea ad alias maiores. Numquam dolor eaque recusandae eius nihil quibusdam esse nisi asperiores in nulla dicta distinctio aliquid ullam aspernatur ad amet fugit dolorem sapiente veritatis quo, rem a. Molestiae qui sed enim! Laborum doloribus nisi quia dolore obcaecati reiciendis quae, numquam consectetur sed animi temporibus inventore nemo. Asperiores fugit rerum, placeat quas in nobis obcaecati similique quae assumenda culpa ab sit voluptatum molestiae voluptates facilis optio recusandae tempore nihil atque et nesciunt nemo perferendis adipisci harum. Corrupti eum autem veritatis et beatae reiciendis, deleniti corporis, voluptas, eaque ipsa nemo odio? Eligendi sapiente id enim quibusdam, repudiandae optio error! Vitae perspiciatis voluptatem sequi nostrum necessitatibus culpa fuga ...",
    dateTime: "15/05/2022",
};

function BlogCard(){

    return (
        <div className="container">
        <div className="mrblog-card-container">
          <div className="mrblog-card">
            <h2>{blog.title}</h2>
            <p className="mrblog-line">{blog.titleDescription}</p>
            <p className="mrblog-date">{blog.dateTime}</p>
            <div className="mrblog-card-description">
              <p>{blog.description}</p>
              <Link to={'/form/${movie.id}'}>
                <div className="btn btn-primary mrblog-btn">Ler mais</div>
              </Link>
            </div>
          </div>
        </div>
      </div>
    )

}
export default BlogCard;