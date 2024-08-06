import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { BASE_URL } from "../../utils/request";
import NotificationButton from '../NotificationButton'
import CreateButton from "../CreateButton";
import { Link } from "react-router-dom"
import './styles.css'
import { Candidato } from "../../models/candidato";
import { string } from "yup";

function VotarCard() {
 
    const [num2, setNumber] = useState('');

    const [candidatos, setCandidatos] = useState<Candidato[]>([]);

    useEffect(() => {

        const dnum = num2;

        axios.get(`${BASE_URL}/candidatos/votar?number=${dnum}`)
            .then(response => {
                setCandidatos(response.data.content);
            })
    }, [num2]);

    return (
        <>
            <div className="dsmeta-card">
                <h2 className="dsmeta-sales-title">Votação</h2>
                <div>
                    <div className="dsmeta-form-control-container">
                    <input value={num2} name="num2" onChange={e => setNumber (e.target.value)}/>
                    </div>
                    <Link to={{ pathname: `${num2}` }}>
                                            <button>Votar</button>
                                        </Link>
                </div>

                <div>
                    <table className="dsmeta-sales-table">
                        <thead>
                            <tr>
                                <th className="show992">ID</th>
                                <th>Nome</th>
                                <th className="show576">Descricao</th>
                                <th>Número</th>
                                <th>Votos</th>
                            </tr>
                        </thead>
                        <tbody>
                            {candidatos.map(candidato => {
                                return (

                                    <><tr key={candidato.id}>
                                        <td className="show992">{candidato.id}</td>
                                        <td>{candidato.nome}</td>
                                        <td className="show576">{candidato.descricao}</td>
                                        <td>{candidato.numero}</td>
                                        <td>{candidato.votos}</td>
                                    </tr></>
                                
                                )
                                
                            })}
                            
                        </tbody>

                    </table>
                </div>

            </div>
        </>
    )
}
export default VotarCard