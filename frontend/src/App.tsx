import Header from "./components/Header"
import EleitoresCard from "./components/EleitoresCard"
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <>
    <ToastContainer />
    <Header />
    <main>
      <section id="sales">
        <div className="dsmeta-container">
          <EleitoresCard />
        </div>
        </section>
      </main>
    </> 
  )
}

export default App
