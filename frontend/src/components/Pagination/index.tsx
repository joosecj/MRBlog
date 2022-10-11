import { ReactComponent as Arrow } from 'assets/img/arrow.svg';
import { BlogPage } from 'types/blog';
import './styles.css';

type Props = {
    page: BlogPage;
    onChange: Function;
}

function Pagination({ page, onChange } : Props) {

    return (
        <div className="mrblog-pagination-container">
            <div className="mrblog-pagination-box">
                <button className="mrblog-pagination-button" disabled={page.first} onClick={() => 
                    onChange(page.number - 1)} >
                    <Arrow />
                </button>
                <p>{`${page.number + 1} de ${page.totalPages}`}</p>
                <button className="mrblog-pagination-button" disabled={page.last} onClick={() => 
                onChange(page.number + 1)} >
                    <Arrow className="mrblog-flip-horizontal" />
                </button>
            </div>
        </div>
    );
}
export default Pagination;