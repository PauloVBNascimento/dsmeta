import Header from "../../components/Header";
import { useForm } from 'react-hook-form'
import axios from "axios";
import { BASE_URL } from "../../utils/request";
import { useNavigate } from 'react-router-dom';

import './styles.css'



function Criar() {

    const navigate = useNavigate()

    const {register, handleSubmit, formState: {errors}} = useForm()

    const min = new Date(new Date().setDate(new Date().getDate()));

    const dmin = min.toISOString().slice(0, 10);
    
    
    
    


    const criarEleitor = (dados: any) => axios.post(`${BASE_URL}/eleitores/criar`, dados)
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
                        <form onSubmit={handleSubmit(criarEleitor)}>
                            <div className="fields">
                                <label>Nome</label>
                                <input type="text" {...register("nome")}/>
                            </div>
                            <div className="fields">
                                <label>Email</label>
                                <input type="text" {...register("email")}/>
                            </div>
                            <div className="fields">
                                <label>Senha</label>
                                <input type="password" {...register("senha")}/>
                            </div>
                            <div className="fields">
                                <label>Telefone</label>
                                <input type="text" {...register("telefone")}/>
                            </div>
                            <div className="fields">
                                <label>Data de Ingresso</label>
                                <input type="date" value={dmin} {...register("dataentrada")}/>
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
export default Criar