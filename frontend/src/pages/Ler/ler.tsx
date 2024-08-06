import React from "react";
import { ToastContainer } from "react-toastify";
import EleitoresCard from "../../components/EleitoresCard";
import Header from "../../components/Header";
import { Link } from "react-router-dom"

function Ler() {
    return (
        <>
        <ToastContainer />
        <Header />
        <main>
          <section id="sales">
            <div className="dsmeta-container">
              <EleitoresCard />
            </div>
            <div>
            <Link to="/eleitores/criar">
                <button>Criar</button>
            </Link>
            </div>
            </section>
          </main>
          </>
    )
}
export default Ler