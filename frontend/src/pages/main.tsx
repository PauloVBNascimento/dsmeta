import { Link } from "react-router-dom"

function MainPag() {
    return (
        <div>
            <h1>Page Main</h1>

            <Link to="/eleitores/criar">
                <button>Criar</button>
            </Link>
        </div>
    )
}
export default MainPag