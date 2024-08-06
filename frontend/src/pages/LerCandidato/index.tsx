import { ToastContainer } from "react-toastify";
import CandidatosCard from "../../components/CandidatosCard";
import Header from "../../components/Header";
import { Link } from "react-router-dom"

function LerCandidato() {
    return (
        <>
        <ToastContainer />
        <Header />
        <main>
          <section id="sales">
            <div className="dsmeta-container">
              <CandidatosCard />
            </div>
            <div>
            <Link to="/candidatos/criar">
                <button>Criar</button>
            </Link>
            </div>
            </section>
          </main>
          </>
    )
}
export default LerCandidato