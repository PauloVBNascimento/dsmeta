import Header from "./components/Header"
import EleitoresCard from "./components/EleitoresCard"


function App() {
  return (
    <>
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
