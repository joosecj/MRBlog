import { ReactComponent as Arrow } from 'assets/img/arrow.svg';
import './styles.css';

function Pagination() {

    return (
        <div className="mrblog-pagination-container">
            <div className="mrblog-pagination-box">
                <button className="mrblog-pagination-button" disabled={true} >
                    <Arrow />
                </button>
                <p>{`${1} de ${3}`}</p>
                <button className="mrblog-pagination-button" disabled={false} >
                    <Arrow className="mrblog-flip-horizontal" />
                </button>
            </div>
        </div>
    );
}
export default Pagination;