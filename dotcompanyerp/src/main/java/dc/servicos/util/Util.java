package dc.servicos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Calendar;

public class Util {

	public static void copiaArquivo(String origem, String destino)
			throws Exception {
		FileInputStream in = new FileInputStream(origem);
		FileOutputStream out = new FileOutputStream(destino);
		byte[] bb = new byte[in.available()];
		in.read(bb);
		out.write(bb);
		out.close();
		in.close();
	}

	public static String md5Arquivo(String nomeArquivo) {
		MessageDigest digest = null;

		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		File f = new File(nomeArquivo);
		InputStream is = null;

		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		byte[] buffer = new byte[8192];
		int read = 0;

		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}

			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			String output = bigInt.toString(16);

			return output;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static byte[] geraAssinaturaArquivo(byte[] arquivoAssinar,
			File arquivoCertificado, char[] senha) {
		try {
			// Carrega o KeyStore
			KeyStore ks = KeyStore.getInstance("PKCS12");
			InputStream isCertificado = new FileInputStream(arquivoCertificado);
			ks.load(isCertificado, senha);

			// pega a chave privada
			Key key = ks.getKey(ks.aliases().nextElement(), senha);
			PrivateKey privateKey = (PrivateKey) key;

			// cria o objeto Signature infomando o algoritmo de assinatura
			// http://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html
			Signature sign = Signature.getInstance("SHA1withRSA");
			sign.initSign(privateKey);

			sign.update(arquivoAssinar);

			// gera a assinatura
			byte[] assinatura = sign.sign();

			return assinatura;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static byte[] lerBytesArquivo(File tmpFile) {
		FileInputStream fis;
		byte[] temp = null;

		try {
			fis = new FileInputStream(tmpFile);
			temp = new byte[(int) tmpFile.length()];
			fis.read(temp);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return temp;
	}

	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			File pastaPai = destFile.getParentFile();

			if (pastaPai != null) {
				pastaPai.mkdirs();
				destFile.createNewFile();
			}
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();

			// previous code: destination.transferFrom(source, 0,
			// source.size());
			// to avoid infinite loops, should be:
			long count = 0;
			long size = source.size();

			while ((count += destination.transferFrom(source, count, size
					- count)) < size)
				;
		} finally {
			if (source != null) {
				source.close();
			}

			if (destination != null) {
				destination.close();
			}

			sourceFile.delete();
		}
	}

	public static File gravarArquivo(String caminho, byte[] dados)
			throws IOException {
		File arquivo = new File(caminho);
		FileOutputStream fos = null;

		try {
			if (!arquivo.exists()) {
				File pastaPai = arquivo.getParentFile();
				if (pastaPai != null) {
					pastaPai.mkdirs();
					arquivo.createNewFile();
				}
			}

			fos = new FileOutputStream(arquivo);
			fos.write(dados);
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return arquivo;
	}

	/**
	 * Verifica se a data informada e valida
	 * 
	 * @param dia
	 *            , mes e ano
	 * @return true se a data for valida, caso contrario false
	 */
	public static boolean validaData(int dia, int mes, int ano) {
		try {
			Calendar dataValidar = Calendar.getInstance();
			dataValidar.setLenient(false);
			dataValidar.set(Calendar.DAY_OF_MONTH, dia);
			dataValidar.set(Calendar.MONTH, mes);
			dataValidar.set(Calendar.YEAR, ano);
			dataValidar.getTime();

			return true;
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return false;
	}

	/**
	 * Verifica se a hora informada e valida
	 * 
	 * @param hora
	 *            , minuto e segundo
	 * @return true se a hora for valida, caso contrario false
	 */
	public static boolean validaHora(int hora, int minuto, int segundo) {
		try {
			Calendar dataValidar = Calendar.getInstance();
			dataValidar.setLenient(false);
			dataValidar.set(Calendar.HOUR_OF_DAY, hora);
			dataValidar.set(Calendar.MINUTE, minuto);
			dataValidar.set(Calendar.SECOND, segundo);
			dataValidar.getTime();

			return true;
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return false;
	}

	/**
	 * Oobtem a hora do dia em segundos
	 * 
	 * @param dataMarcacao
	 * @return int - hora do dia em segundos
	 */
	public static int getHoraSeg(Calendar dataMarcacao) {
		int segundos = dataMarcacao.get(Calendar.SECOND);
		segundos += dataMarcacao.get(Calendar.MINUTE) * 60;
		segundos += dataMarcacao.get(Calendar.HOUR_OF_DAY) * 3600;

		return segundos;
	}

	/**
	 * Converte segundos para horas:minutos:segundos
	 * 
	 * @param segundos
	 * @return String no formato HH:mm:ss
	 */
	public static String getHoraMinutoSegundo(int segundos) {
		Calendar dataC = Calendar.getInstance();
		dataC.set(Calendar.HOUR_OF_DAY, 0);
		dataC.set(Calendar.MINUTE, 0);
		dataC.set(Calendar.SECOND, 0);

		dataC.add(Calendar.SECOND, segundos);

		String resultado = dataC.get(Calendar.HOUR_OF_DAY) < 10 ? "0"
				+ dataC.get(Calendar.HOUR_OF_DAY) : ""
				+ dataC.get(Calendar.HOUR_OF_DAY);
		resultado += ":";
		resultado += dataC.get(Calendar.MINUTE) < 10 ? "0"
				+ dataC.get(Calendar.MINUTE) : "" + dataC.get(Calendar.MINUTE);
		resultado += ":";
		resultado += dataC.get(Calendar.SECOND) < 10 ? "0"
				+ dataC.get(Calendar.SECOND) : "" + dataC.get(Calendar.SECOND);

		return resultado;
	}

	/**
	 * Converte a hora de String para Calendar
	 * 
	 * @param horas
	 *            no formato HH:mm:ss
	 * @return Calendar
	 */
	public static Calendar horaStrToCalendar(String horas) {
		Calendar dataC = Calendar.getInstance();
		dataC.set(Calendar.HOUR_OF_DAY, Integer.valueOf(horas.substring(0, 2)));
		dataC.set(Calendar.MINUTE, Integer.valueOf(horas.substring(3, 5)));
		dataC.set(Calendar.SECOND, Integer.valueOf(horas.substring(6, 8)));

		return dataC;
	}

}