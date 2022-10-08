import { ReactComponent as Arrow } from 'assets/img/arrow.svg';
import './styles.css';

function Pagination() {

    return (
        <div className="dsplaygames-pagination-container">
            <div className="dsplaygames-pagination-box">
                <button className="dsplaygames-pagination-button" disabled={true} >
                    <Arrow />
                </button>
                <p>{`${1} de ${3}`}</p>
                <button className="dsplaygames-pagination-button" disabled={false} >
                    <Arrow className="dsplaygames-flip-horizontal" />
                </button>
            </div>
        </div>
    );
}
export default Pagination;