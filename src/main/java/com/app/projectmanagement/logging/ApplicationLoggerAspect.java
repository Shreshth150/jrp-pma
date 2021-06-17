package com.app.projectmanagement.logging;

//
//@Aspect
//@Component
//public class ApplicationLoggerAspect {
//	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//	
//	@Pointcut("within(com.jrp.pma.controllers..*)")
//	public void definePackagePointcuts() {
//		// empty method just to name the location specified in the pointcut
//	}
//	
//	@Around("definePackagePointcuts()")
//	public Object logAround(ProceedingJoinPoint jp) {
//		log.debug(" \n \n \n ");
//		log.debug("************ Before Method Execution ************ \n {}.{} () with arguments[s] = {}",
//				jp.getSignature().getDeclaringTypeName(),
//				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//		log.debug("_________________________________________________ \n \n \n");
//		
//		Object o = null;
//		
//		try {
//			o = jp.proceed();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		log.debug("************ After Method Execution ************ \n {}.{} () with arguments[s] = {}",
//				jp.getSignature().getDeclaringTypeName(),
//				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//		log.debug("_________________________________________________ \n \n \n");
//		
//		return o;
//	}
//}
