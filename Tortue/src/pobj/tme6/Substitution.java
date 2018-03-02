package pobj.tme6;
import java.util.List;

public class Substitution {
	
	static public ICommand substitute(ICommand org, ICommand subst) {
		/*if (org instanceof CommandList) {
			int i = 0;
			for (ICommand c: ((CommandList) org).getCommandList()) {
				if (c instanceof CommandMove) {
					((CommandList) org).replaceCommand(i, subst);
				}
				i++;
			}
		} else {
			if (org instanceof CommandMove) {
				org = subst;
			}
		}
		return org;
	}*/
	
	CommandList my_org = (CommandList) org;
	CommandList malist = new CommandList();
		
	List<ICommand> cl =  my_org.getCommandList();
	for (ICommand c : cl){
		if ( c instanceof CommandMove) {
			if (subst instanceof CommandList) {
				List<ICommand> lsubst =  ((CommandList) subst).getCommandList();
				for (ICommand c_subst : lsubst) {
					malist.addCommand(c_subst);
				}
			} else {
				malist.addCommand(subst);
			}
		}else {
			malist.addCommand(c);
		}
	}
	return malist;

	}
}

