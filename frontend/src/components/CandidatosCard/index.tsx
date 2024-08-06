import axios from "axios";
import { useEffect, useState } from "react";
import "react-datepicker/dist/react-datepicker.css";
import { Candidato } from "../../models/candidato";
import { BASE_URL } from "../../utils/request";
import NotificationButton from '../NotificationButton'
import { Link } from "react-router-dom"
import './styles.css'


function CandidatosCard() {

    const [candidatos, setCandidatos] = useState<Candidato[]>([]);
    
    useEffect(() => {

        axios.get(`${BASE_URL}/candidatos`)
            .then(response => {
                setCandidatos(response.data.content);
            })
            
    }, []);

    function deletarCandidato(id: number) {
        axios.delete(`${BASE_URL}/candidatos/${id}`)

        setCandidatos(candidatos.filter(candidato => candidato.id !== id))
        
    }   

    return (
        <>
            <div className="dsmeta-card">
                <h2 className="dsmeta-sales-title">Candidatos</h2>
                <div>
                    <table className="dsmeta-sales-table">
                        <thead>
                            <tr>
                                <th className="show992">ID</th>
                                <th>Nome</th>
                                <th className="show576">Descricao</th>
                                <th className="show576">Telefone</th>
                                <th>Número</th>
                                <th>Votos</th>
                                <th>Notificar</th>
                            </tr>
                        </thead>
                        <tbody>
                            {candidatos.map(candidato => {
                                return (

                                    <tr key={candidato.id}>
                                        <td className="show992">{candidato.id}</td>
                                        <td>{candidato.nome}</td>
                                        <td className="show576">{candidato.descricao}</td>
                                        <td className="show576">{candidato.telefone}</td>
                                        <td>{candidato.numero}</td>
                                        <td>{candidato.votos}</td>
                                        <td>
                                            <div className="dsmeta-red-btn-container">
                                                <NotificationButton Id={candidato.id} />
                                            </div>
                                        </td>
                                        <div className="dsmeta-red-btn-container">
                                            <Link to={{ pathname: `${candidato.id}/editar` }}>
                                                <button>Edit</button>
                                            </Link>
                                        </div>
                                        <div className="dsmeta-red-btn-container">

                                            <button onClick={() => deletarCandidato(candidato.id)}>Delete</button>

                                        </div>
                                    </tr>
                                
                                )
                                
                            })}
                        </tbody>

                    </table>
                </div>

            </div>
        </>
    )
}
export default CandidatosCard