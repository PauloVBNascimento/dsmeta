import Header from "../../components/Header";
import { useForm } from 'react-hook-form'
import axios from "axios";
import { BASE_URL } from "../../utils/request";
import { useNavigate } from 'react-router-dom';

import './styles.css'



function CriarCandidato() {

    const navigate = useNavigate()

    const {register, handleSubmit, formState: {errors}} = useForm()

    const criarCandidato = (dados: any) => axios.post(`${BASE_URL}/candidatos/criar`, dados)
                            .then(() => {
                                console.log(dados)
                                navigate('/')
                            })
                            .catch (() => {
                               
                            })

    return (
        <div>
            <Header/>
            <main>
                <div className="card-post">
                    <h1>Criar Eleitor</h1>
                    <div className="line-post"></div>    
                    <div className="card-body-post">
                        <form onSubmit={handleSubmit(criarCandidato)}>
                            <div className="fields">
                                <label>Nome do Candidato</label>
                                <input type="text" {...register("nome")}/>
                            </div>
                            <div className="fields">
                                <label>Descrição</label>
                                <textarea {...register("descricao")}/>
                            </div>
                            <div className="fields">
                                <label>Telefone</label>
                                <input type="text" {...register("telefone")}/>
                            </div>
                            <div className="fields">
                                <label>Número</label>
                                <input type="number" placeholder="000000" {...register("numero")}/>
                            </div>
                            <div className="btn-post">
                                <button type="submit">Enviar</button>
                            </div>
                        </form>
                    </div>
                </div> 
                    
                
            </main>
        </div>
    )
}
export default CriarCandidato