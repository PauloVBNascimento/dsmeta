import axios from 'axios';
import { toast } from 'react-toastify';
import iconesms from '../../assets/img/notification-icon.svg'
import { BASE_URL } from '../../utils/request';
import './styles.css'

type Props = {
    Id: number;
}

function cuidarClick (id :number) {
    axios(`${BASE_URL}/eleitores/${id}/notificar`)
          .then(response => {
            toast.info("SMS enviado com sucesso")
          })
}

function NotificationButton( {Id} : Props ) {
    return (
        <div className="dsmeta-red-btn" onClick={() => cuidarClick(Id)}>
        <img src={iconesms} alt="Notificar" />
      </div>
    )
  }

  
  export default NotificationButton