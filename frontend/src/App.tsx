import Header from "./components/Header"
import EleitoresCard from "./components/EleitoresCard"
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter, Routes, Route, Link, Router } from "react-router-dom"
import Ler from './pages/Ler/ler'
import MainPag from "./pages/main";
import Edit from "./pages/Edit/edit";
import Criar from "./pages/Post/post";
import LerUrna from "./pages/LerCandidato";
import CriarCandidato from "./pages/PostCandidato";
import EditCandidato from "./pages/EditCandidato";
import Votar from "./pages/Votar";


function App() {
  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPag/>} />
        <Route path="/eleitores/criar" element={<Criar/>} />
        <Route path="/eleitores" element={<Ler/>} />
        <Route path="/eleitores/:id/editar" element={<Edit/>} />
        <Route path="/candidatos" element={<LerUrna/>} />
        <Route path="/candidatos/criar" element={<CriarCandidato/>} />
        <Route path="/candidatos/:id/editar" element={<EditCandidato/>} />
        <Route path="/candidatos/votar" element={<Votar/>} />
        <Route path="/candidatos/votar/:number"/>
      </Routes>
    </BrowserRouter>
    </> 
  )
}

export default App
