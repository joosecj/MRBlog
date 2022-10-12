import './styles.css';
import { Link } from "react-router-dom";

function Navbar() {
    return (
    <header>
    <nav className="container">
    <Link to='/'> 
      <div className="nav-brand">MRBlog</div>
    </Link>
      <a className="menu-items" href="#news">Notícias</a>
    </nav>
    </header>
    );
}
export default Navbar;