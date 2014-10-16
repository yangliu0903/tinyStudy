package org.tinygroup.netshopbiz;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.commons.cryptor.Cryptor;
import org.tinygroup.commons.cryptor.DefaultCryptor;
import org.tinygroup.context.Context;
import org.tinygroup.entity.entitymodel.EntityModel;
import org.tinygroup.imda.processor.ModelServiceProcessor;
import org.tinygroup.imda.tinyprocessor.ModelRequestInfo;
import org.tinygroup.tinydb.Bean;
import org.tinygroup.tinydb.BeanOperatorManager;
import org.tinygroup.tinydb.operator.DBOperator;

/**
 * 
 * 功能说明:修改密码服务
 * <p>
 * 系统版本: v1.0<br>
 * 开发人员: renhui <br>
 * 开发时间: 2013-10-14 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class ModifyPasswordService implements
		ModelServiceProcessor<EntityModel, Integer> {

	private Cryptor cryptor = new DefaultCryptor();

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}

	@SuppressWarnings("unchecked")
	public Integer process(ModelRequestInfo modelRequestInfo, Context context) {

		try {
			String userUuid=context.get("userUuid");
			String userId = context.get("userId");
			String oldPassword = cryptor.encrypt(
					(String) context.get("oldPassword"), userId);
			String newPassword = cryptor.encrypt(
					(String) context.get("newPassword"), userId);
			BeanOperatorManager manager =BeanContainerFactory.getBeanContainer(this.getClass().getClassLoader())
					.getBean(BeanOperatorManager.OPERATOR_MANAGER_BEAN);
			DBOperator<String> operator = (DBOperator<String>) manager
					.getDbOperator("user");
			Bean user = new Bean("user");
			user.setProperty("userId", userId);
			user.setProperty("userUuid", userUuid);
			user.setProperty("password", oldPassword);
			Bean[] exist = operator.getBeans(user);
			if (exist != null && exist.length > 0) {
				Bean updateUser = new Bean("user");
				updateUser.setProperty("userPassword", newPassword);
				updateUser.setProperty("userId", userUuid);
				return operator.update(updateUser);

			} else {
				throw new RuntimeException("旧密码输入不正确");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
