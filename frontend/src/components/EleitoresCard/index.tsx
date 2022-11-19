import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { Eleitor } from "../../models/eleitor";
import { BASE_URL } from "../../utils/request";
import NotificationButton from '../NotificationButton'
import './styles.css'

function EleitoresCard() {

    const min = new Date(new Date().setDate(new Date().getDate() - 365));
    const max = new Date();

    const [minDate, setMinDate] = useState(min);
    const [maxDate, setMaxDate] = useState(max);

    const [eleitores, setEleitores] = useState<Eleitor[]>([]);

    useEffect(() => {

        const dmin = minDate.toISOString().slice(0, 10);
        const dmax = maxDate.toISOString().slice(0, 10);

        axios.get(`${BASE_URL}/eleitores?minDate=${dmin}&maxDate=${dmax}`)
                .then(response => {
                    setEleitores(response.data.content);
                })
    }, [minDate,maxDate]);

    return (
        <>
            <div className="dsmeta-card">
                <h2 className="dsmeta-sales-title">Eleitores</h2>
                <div>
                    <div className="dsmeta-form-control-container">
                        <DatePicker
                            selected={minDate}
                            onChange={(date: Date) =>  setMinDate(date)}
                            className="dsmeta-form-control"
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                    <div className="dsmeta-form-control-container">
                        <DatePicker
                            selected={maxDate}
                            onChange={(date: Date) => setMaxDate(date)}
                            className="dsmeta-form-control"
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                </div>

                <div>
                    <table className="dsmeta-sales-table">
                        <thead>
                            <tr>
                                <th className="show992">ID</th>
                                <th>Eleitor</th>
                                <th className="show576">Email</th>
                                <th className="show992">Telefone</th>
                                <th className="show576">Data-Entrada</th>
                                <th>Notificar</th>
                            </tr>
                        </thead>
                        <tbody>
                            {eleitores.map(eleitor => {
                                return(
                                    <tr key={eleitor.id}>
                                <td className="show992">{eleitor.id}</td>
                                <td>{eleitor.nome}</td>
                                <td className="show576">{eleitor.email}</td>
                                <td className="show992">{eleitor.telefone}</td>
                                <td className="show576">{new Date(eleitor.dataentrada).toLocaleDateString
                                ()}</td>
                                <td>
                                    <div className="dsmeta-red-btn-container">
                                        <NotificationButton />
                                    </div>
                                </td>
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
export default EleitoresCard