import './styles.css';

function Navbar() {
    return (
    <header>
    <nav className="container">
      <a className="nav-brand" href="/">MRBlog</a>
      <a className="menu-items" href="#news">Notícias</a>
    </nav>
    </header>
    );
}
export default Navbar;