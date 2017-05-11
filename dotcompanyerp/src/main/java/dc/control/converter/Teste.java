package dc.control.converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

import dc.controller.patrimonio.ApoliceSeguroFormController;
import dc.entidade.patrimonio.ApoliceSeguroEntity;
import dc.visao.patrimonio.ApoliceSeguroFormView;

public class Teste {

	static List<Class<?>> auxLista = new ArrayList<Class<?>>();

	static Class<?> comboBoxType = ComboBox.class;
	static Class<?> textFieldType = TextField.class;
	static Class<?> popupDateField = PopupDateField.class;

	static Class<?> myType = Long.TYPE;
	static Class<?> integerType = Integer.TYPE;
	static Class<?> stringClass = String.class;

	/**
	 * SUBVIEW > ENTITY
	 * 
	 * @param subview
	 * @param entity
	 */
	public static Object entityToSubview(Object subview, Object entity) {
		try {
			Class<?> classeSubview = subview.getClass();
			Class<?> classe2 = entity.getClass();

			for (Field atributoSubview : classeSubview.getDeclaredFields()) {
				if (atributoSubview
						.isAnnotationPresent(dc.control.converter.RunField.class)) {
					String mapaSubview = atributoSubview.getAnnotation(
							RunField.class).mappedName();

					String nomeSubview = atributoSubview.getName();
					String tipoSubview = atributoSubview.getType().getName();
					String membroClasseSubview = atributoSubview
							.getDeclaringClass().getName();

					atributoSubview.setAccessible(true);

					if (mapaSubview != null && !mapaSubview.isEmpty()
							&& mapaSubview.length() != 0) {
						Field atributoEntity = classe2
								.getDeclaredField(mapaSubview);

						String nomeEntity = atributoEntity.getName();
						String tipoEntity = atributoEntity.getType().getName();
						String membroClasseEntity = atributoEntity
								.getDeclaringClass().getName();

						atributoEntity.setAccessible(true);

						if (nomeEntity.equals(mapaSubview)) {
							System.out.println(mapaSubview + " -- "
									+ nomeSubview + " <> " + nomeEntity);

							System.out.println("-- " + tipoSubview + " : "
									+ tipoEntity);

							System.out.println("-- " + membroClasseSubview
									+ " : " + membroClasseEntity);

							System.out.println("--------");

							// if (atributoSubview.getType().isAssignableFrom(
							// comboBoxType)) {
							// ComboBox cb = ((ComboBox) atributoSubview
							// .get(subview));

							// String tipoEntityClasse = tipoEntity;
							// Class<?> clazz = Class
							// .forName(tipoEntityClasse);
							// Object newValue = clazz.cast(cb.getValue());

							// atributoEntity.set(entity, newValue);

							// System.out.println("PPPPPPPPPPP: "
							// + atributoEntity.get(entity));
							// }

							if (atributoSubview.getType().isAssignableFrom(
									textFieldType)) {
								TextField cb = ((TextField) atributoSubview
										.get(subview));
								// System.out.println("......." + t.getValue());

								// TextField t = ((TextField)
								// atributoSubview.get(classeSubview));
								// t.setValue("teste");
								// System.out.println("......." + v);
								// ComboBox cb = (ComboBox) objSubview;
								// atributoSubview.get(nomeSubview);

								String tipoEntityClasse = tipoEntity;
								Class<?> clazz = Class
										.forName(tipoEntityClasse);
								Object newValue = clazz.cast(cb.getValue());

								System.out.println(">>>>>" + newValue);

								if (tipoEntity.equals("java.lang.Double")) {

									atributoEntity.set(entity, (Double
											.parseDouble((String) newValue)));
								} else {
									atributoEntity.set(entity, newValue);

									break;
								}

								System.out.println("PPPPPPPPPPP: "
										+ atributoEntity.get(entity));
							}

							// break;

							// System.out.println("-- " + nomeEntity + " : "
							// + tipoEntity);

							// atributo2.set(nomeAtributo2,
							// (atributo.get(classe1).));
						}
					}
				}
			}

			return entity;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static void entityToSubview1(Object subview, Object entity) {
		auxLista.add(ComboBox.class);
		auxLista.add(TextField.class);
		auxLista.add(PopupDateField.class);

		try {
			Class<?> classe1 = entity.getClass();

			// Class<?> classe1 = Class.forName(entity.getClass().getName());

			for (Field atributo : classe1.getDeclaredFields()) {
				atributo.setAccessible(true);

				if (atributo
						.isAnnotationPresent(dc.control.converter.RunField.class)) {

					String mapa = atributo.getAnnotation(RunField.class)
							.mappedName();

					System.out.println("mapa::::::: " + mapa);

					System.out.println("----------");
					// atributo.setAccessible(true);

					String nomeAtributo = atributo.getName();
					String membroClasse = atributo.getDeclaringClass()
							.getName();
					String tipo = atributo.getType().getName();

					System.out.println(nomeAtributo);
					// System.out.println(membroClasse);
					System.out.println(tipo);
					// atributo.setAccessible(true);

					Object v = atributo.get(entity);

					System.out.println("valor rrrr  = " + v);

					// atributo.setAccessible(true);
					// System.out
					// .println("valor rrrr  = " + (String)
					// atributo.get(classe1));

					// TextField t = ((TextField) atributo.get(p1));
					// t.setValue("teste");

					// System.out.println("valor yyyyyy  = "
					// + atributo.get(subview));
				}
			}

			Class<?> classe = subview.getClass();

			System.out.println("A classe " + classe.getSimpleName()
					+ " possui os atributos:");

			for (Field atributo : classe.getDeclaredFields()) {
				if (atributo
						.isAnnotationPresent(dc.control.converter.RunField.class)) {
					System.out.println("----------");

					String mapa = atributo.getAnnotation(RunField.class)
							.mappedName();

					System.out.println("mapa::::::: " + mapa);

					System.out.println("nome atributo = " + atributo.getName());
					System.out.println("membro da classe = "
							+ atributo.getDeclaringClass());
					System.out.println("tipo = " + atributo.getType());
					atributo.setAccessible(true);
					// System.out
					// .println("valor rrrr  = " + atributo.get(classe));

					// TextField t = ((TextField) atributo.get(p1));
					// t.setValue("teste");

					System.out.println("valor yyyyyy  = "
							+ atributo.get(subview));
				}

				/*
				 * if (auxLista.contains(atributo.getType())) {
				 * 
				 * System.out.println("-------------");
				 * 
				 * System.out.println("nome atributo = " + atributo.getName());
				 * System.out.println("membro da classe = " +
				 * atributo.getDeclaringClass()); System.out.println("tipo = " +
				 * atributo.getType());
				 * 
				 * if (atributo.getType().isAssignableFrom(comboBoxType)) {
				 * System.out.println("><><><><> comboBoxType");
				 * atributo.setAccessible(true);
				 * System.out.println("valor rrrr  = " + atributo.get(subview));
				 * }
				 * 
				 * if (atributo.getType().isAssignableFrom(textFieldType)) {
				 * System.out.println("><><><><> textFieldType");
				 * atributo.setAccessible(true);
				 * System.out.println("valor rrrr  = " + atributo.get(subview));
				 * 
				 * TextField t = ((TextField) atributo.get(subview));
				 * t.setValue("teste");
				 * 
				 * System.out.println("valor yyyyyy  = " +
				 * atributo.get(subview)); }
				 * 
				 * if (atributo.getType().isAssignableFrom(popupDateField)) {
				 * System.out.println("><><><><> popupDateField");
				 * atributo.setAccessible(true);
				 * System.out.println("valor rrrr  = " + atributo.get(subview));
				 * }
				 * 
				 * // int mod = atributo.getModifiers(); //
				 * System.out.println("modificadores = " // +
				 * Modifier.toString(mod)); }
				 */
			}

			System.out.println("<><><><><><><><><><><");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void subviewToEntity(ApoliceSeguroEntity p1) { try {
	 * Class<?> myType = Long.TYPE;
	 * 
	 * Class<?> classe = p1.getClass();
	 * 
	 * System.out.println("A classe " + classe.getSimpleName() +
	 * " possui os atributos:");
	 * 
	 * for (Field atributo : classe.getDeclaredFields()) {
	 * System.out.println("-------------");
	 * 
	 * System.out.println("nome atributo = " + atributo.getName());
	 * System.out.println("membro da classe = " + atributo.getDeclaringClass());
	 * System.out.println("tipo = " + atributo.getType());
	 * 
	 * if (atributo.getType().isAssignableFrom(integerType)) {
	 * System.out.println("><><><><> inteiro"); }
	 * 
	 * if (atributo.getType().isAssignableFrom(stringClass)) {
	 * System.out.println("><><><><> string"); }
	 * 
	 * atributo.setAccessible(true); System.out.println("valor rrrr  = " +
	 * atributo.get(p1)); // Object value = field.get(instance);
	 * 
	 * int mod = atributo.getModifiers(); System.out.println("modificadores = "
	 * + Modifier.toString(mod)); } } catch (Exception e) { e.printStackTrace();
	 * } }
	 */

	/**
	 * SUBVIEW > ENTITY
	 * 
	 * @param subview
	 * @param entity
	 */
	public static Object subviewToEntity(Object entity, Object subview) {
		try {
			Class<?> classeEntity = entity.getClass();

			Object obj = classeEntity.newInstance();

			Class<?> classeSubview = subview.getClass();

			for (Field atrEntity : classeEntity.getDeclaredFields()) {
				if (atrEntity
						.isAnnotationPresent(dc.control.converter.RunField.class)) {
					String mapaSubview = atrEntity
							.getAnnotation(RunField.class).mappedName();

					String nomeSubview = atrEntity.getName();
					String tipoSubview = atrEntity.getType().getName();

					Field msgField = classeEntity.getDeclaredField(nomeSubview);
					msgField.setAccessible(true);

					if (tipoSubview.equals("java.lang.Double")) {
						msgField.set(obj, new Double(10000));
					} else if (tipoSubview.equals("java.lang.String")) {
						String s = "" + mapaSubview.charAt(0);
						s = s.toUpperCase();
						mapaSubview = mapaSubview.replaceFirst(
								"" + mapaSubview.charAt(0), s);
						mapaSubview = "tf" + mapaSubview;

						Field f = classeSubview.getDeclaredField(mapaSubview);
						f.setAccessible(true);

						TextField t = ((TextField) f.get(subview));

						msgField.set(obj, t.getValue());
					}
				}
			}

			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		ApoliceSeguroFormView p1 = new ApoliceSeguroFormView(
				new ApoliceSeguroFormController());

		p1.getTfImagem().setValue("teste");
		;

		ApoliceSeguroEntity p2 = new ApoliceSeguroEntity();

		Teste.subviewToEntity(p2, p1);

	}

}