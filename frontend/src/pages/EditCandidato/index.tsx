import Header from "../../components/Header";
import { useForm } from 'react-hook-form'
import axios from "axios";
import { BASE_URL } from "../../utils/request";
import { useNavigate, useParams } from 'react-router-dom';

import './styles.css'
import { useEffect } from "react";



function EditCandidato() {

    const navigate = useNavigate()

    const {register, handleSubmit, formState: {errors}, reset} = useForm()

    const min = new Date(new Date().setDate(new Date().getDate()));

    const dmin = min.toISOString().slice(0, 10);
    
    const { id } = useParams()

    useEffect(() => {
        axios.get(`${BASE_URL}/candidatos/${id}`)
        .then((response) => {
            reset(response.data)
        })
    }, [])

    const editarCandidato = (dados: any) => axios.put(`${BASE_URL}/candidatos/${id}/editar`, dados)
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
                    <h1>Editar Eleitor</h1>
                    <div className="line-post"></div>    
                    <div className="card-body-post">
                        <form onSubmit={handleSubmit(editarCandidato)}>
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
export default EditCandidato