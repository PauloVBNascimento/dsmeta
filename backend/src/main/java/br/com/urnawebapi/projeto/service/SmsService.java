package br.com.urnawebapi.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.urnawebapi.projeto.model.Eleitor;
import br.com.urnawebapi.projeto.repository.EleitorInterface;

import com.twilio.Twilio;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	@Autowired
	private EleitorInterface eleitorInterface;

	public void sendSms(Integer id) {

		Eleitor eleitor = eleitorInterface.findById(id).get();

		String date = eleitor.getDataentrada().getMonthValue() + "/" + eleitor.getDataentrada().getYear();

		String msg = "Bom dia senhor " + eleitor.getNome() + " você foi convocado a votar em " + date;

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}
