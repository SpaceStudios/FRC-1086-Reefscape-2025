// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.FieldObject2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.FieldConstants.CoralStation;
import frc.robot.FieldConstants.Reef;
import java.util.ArrayList;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;
import org.littletonrobotics.junction.networktables.LoggedNetworkBoolean;

/** Add your docs here. */
public class AutoMaker {
  private static LoggedDashboardChooser<String> positionChooser =
      new LoggedDashboardChooser<String>("Auto Maker/Position");
  private static LoggedDashboardChooser<String> elevatorSetting =
      new LoggedDashboardChooser<String>("Auto Maker/Elevator Setting");
  private static LoggedNetworkBoolean addPosition = new LoggedNetworkBoolean("Auto Maker/Set Pos");
  private static String path;
  private static Trigger addPositionTrigger;
  private static ArrayList<Pose2d> pathTargets = new ArrayList<Pose2d>();
  private static Field2d displayField = new Field2d();
  private static Command tempCommand = Commands.waitSeconds(0.1);
  private static FieldObject2d autoMakerPoses;

  static {
    autoMakerPoses = displayField.getObject("automaker");
    Logger.registerDashboardInput(positionChooser);
    Logger.registerDashboardInput(elevatorSetting);
    Logger.registerDashboardInput(addPosition);
    Logger.recordOutput("Automaker/Path", path);
    addPosition.set(false);
    addPositionTrigger = new Trigger(addPosition::get);
    ;

    positionChooser.addOption("Branch A", "A");

    positionChooser.addOption("Branch B", "B");

    positionChooser.addOption("Branch C", "C");

    positionChooser.addOption("Branch D", "D");

    positionChooser.addOption("Branch E", "E");

    positionChooser.addOption("Branch F", "F");

    positionChooser.addOption("Branch G", "G");

    positionChooser.addOption("Branch H", "H");

    positionChooser.addOption("Branch I", "I");

    positionChooser.addOption("Branch J", "J");

    positionChooser.addOption("Branch K", "K");

    positionChooser.addOption("Branch L", "L");

    positionChooser.addOption("Left Source", "PLO");

    positionChooser.addOption("Right Source", "PRO");

    elevatorSetting.addDefaultOption("Intake", "IN");

    addPositionTrigger.whileTrue(
        Commands.runOnce(
            () -> {
              String compressedPathSetting =
                  positionChooser.get() + "(ES=" + elevatorSetting.get() + ")";
              path += compressedPathSetting;
              System.out.println(compressedPathSetting);
              switch (positionChooser.get()) {
                case "PLO":
                  pathTargets.add(CoralStation.leftCenterFace);
                  break;
                case "PRO":
                  pathTargets.add(CoralStation.rightCenterFace);
                  break;

                case "A":
                  pathTargets.add(Reef.branchesLeft[0]);
                  break;

                case "B":
                  pathTargets.add(Reef.branchesRight[0]);
                  break;

                case "C":
                  pathTargets.add(Reef.branchesLeft[5]);
                  break;

                case "D":
                  pathTargets.add(Reef.branchesRight[5]);
                  break;

                case "E":
                  pathTargets.add(Reef.branchesLeft[4]);
                  break;

                case "F":
                  pathTargets.add(Reef.branchesRight[4]);
                  break;

                case "G":
                  pathTargets.add(Reef.branchesLeft[3]);
                  break;

                case "H":
                  pathTargets.add(Reef.branchesRight[3]);
                  break;

                case "I":
                  pathTargets.add(Reef.branchesLeft[2]);
                  break;

                case "J":
                  pathTargets.add(Reef.branchesRight[2]);
                  break;

                case "K":
                  pathTargets.add(Reef.branchesLeft[1]);
                  break;

                case "L":
                  pathTargets.add(Reef.branchesRight[1]);
                  break;

                default:
                  pathTargets.add(new Pose2d(10, 10, Rotation2d.kZero));
                  break;
              }
              Logger.recordOutput("Auto Maker/Poses", getPoses());
              autoMakerPoses.setPoses(getPoses());
              SmartDashboard.putData("Auto Maker/Field", displayField);
              addPosition.set(false);
            }));
    SmartDashboard.putData("Auto Maker/Field", displayField);
  }

  public static Command get() {
    return Commands.print("Not Ready Yet");
  }

  public static Command customCommand() {
    return tempCommand;
  }

  private static Pose2d[] getPoses() {
    Pose2d[] poses = new Pose2d[pathTargets.size()];
    for (int i = 0; i < pathTargets.size(); i++) {
      poses[i] = pathTargets.get(i);
    }
    return poses;
  }
}
