import { ToastContainer } from "react-toastify";
import VotarCard from "../../components/VotarCard";
import Header from "../../components/Header";
import { Link } from "react-router-dom"

function Votar() {
    return (
        <>
        <ToastContainer />
        <Header />
        <main>
          <section id="sales">
            <div className="dsmeta-container">
              <VotarCard />
            </div>
            <div>
            <Link to=":number">
                <button>Votar</button>
            </Link>
            </div>
            </section>
          </main>
          </>
    )
}
export default Votar