package cn.southplex.commandbridge.common.mqeasy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommandItem {
    public String password;
    public String[] commandLine;
}
